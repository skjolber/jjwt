/*
 * Copyright (C) 2014 jsonwebtoken.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jsonwebtoken.impl.io

import io.jsonwebtoken.io.Serializer
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.orgjson.io.OrgJsonSerializer
import io.jsonwebtoken.gson.io.GsonSerializer
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.easymock.EasyMock.createMock
import static org.junit.Assert.*

class RuntimeClasspathSerializerLocatorTest {

    private static final String TEST_SERVICE_DESCRIPTOR_ORGJSON = "io.jsonwebtoken.io.Serializer.test.orgjson"

    private static final String TEST_SERVICE_DESCRIPTOR_GSON = "io.jsonwebtoken.io.Serializer.test.gson"

    @Before
    void setUp() {
        RuntimeClasspathSerializerLocator.SERIALIZER.set(null)
    }

    @After
    void teardown() {
        RuntimeClasspathSerializerLocator.SERIALIZER.set(null)
    }

    @Test
    void testClassIsNotAvailable() {
        NoServiceDescriptorClassLoader.runWith {
            try {
                new RuntimeClasspathSerializerLocator().getInstance()
                fail 'Located Deserializer class, whereas none was expected.'
            } catch (Exception ex) {
                assertEquals 'Unable to discover any JSON Serializer implementations on the classpath.', ex.message
            }
        }
    }

    @Test
    void testCompareAndSetFalse() {
        Serializer serializer = createMock(Serializer)
        def locator = new RuntimeClasspathSerializerLocator() {
            @Override
            protected boolean compareAndSet(Serializer s) {
                RuntimeClasspathSerializerLocator.SERIALIZER.set(serializer)
                return false
            }
        }

        def returned = locator.getInstance()
        assertSame serializer, returned
    }

    @Test(expected = IllegalStateException)
    void testLocateReturnsNull() {
        def locator = new RuntimeClasspathSerializerLocator() {
            @Override
            protected Serializer<Object> locate() {
                return null
            }
        }
        locator.getInstance()
    }

    @Test(expected = IllegalStateException)
    void testCompareAndSetFalseWithNullReturn() {
        def locator = new RuntimeClasspathSerializerLocator() {
            @Override
            protected boolean compareAndSet(Serializer<Object> s) {
                return false
            }
        }
        locator.getInstance()
    }

    @Test
    void testJackson() {
        def serializer = new RuntimeClasspathSerializerLocator().getInstance()
        assertTrue serializer instanceof JacksonSerializer
    }

    @Test
    void testOrgJson() {
        FakeServiceDescriptorClassLoader.runWithFake TEST_SERVICE_DESCRIPTOR_ORGJSON, {
            def serializer = new RuntimeClasspathSerializerLocator().getInstance()
            assertTrue serializer instanceof OrgJsonSerializer
        }
    }

    @Test
    void testGson() {
        FakeServiceDescriptorClassLoader.runWithFake TEST_SERVICE_DESCRIPTOR_GSON, {
            def serializer = new RuntimeClasspathSerializerLocator().getInstance()
            assertTrue serializer instanceof GsonSerializer
        }
    }
}
