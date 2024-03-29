package com.fasterxml.jackson.core.write;

import java.io.StringWriter;

import com.fasterxml.jackson.core.BaseTest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.core.exc.StreamConstraintsException;

public class WriterBasedJsonGeneratorTest extends BaseTest
{
    private final JsonFactory JSON_MAX_NESTING_1 = JsonFactory.builder()
            .streamWriteConstraints(StreamWriteConstraints.builder().maxNestingDepth(1).build())
            .build();

    public void testNestingDepthWithSmallLimit() throws Exception
    {
        StringWriter sw = new StringWriter();
        try (JsonGenerator gen = JSON_MAX_NESTING_1.createGenerator(sw)) {
            gen.writeStartObject();
            gen.writeFieldName("array");
            gen.writeStartArray();
            fail("expected StreamConstraintsException");
        } catch (StreamConstraintsException sce) {
            String expected = "Document nesting depth (2) exceeds the maximum allowed (1, from `StreamWriteConstraints.getMaxNestingDepth()`)";
            assertEquals(expected, sce.getMessage());
        }
    }

    public void testNestingDepthWithSmallLimitNestedObject() throws Exception
    {
        StringWriter sw = new StringWriter();
        try (JsonGenerator gen = JSON_MAX_NESTING_1.createGenerator(sw)) {
            gen.writeStartObject();
            gen.writeFieldName("object");
            gen.writeStartObject();
            fail("expected StreamConstraintsException");
        } catch (StreamConstraintsException sce) {
            String expected = "Document nesting depth (2) exceeds the maximum allowed (1, from `StreamWriteConstraints.getMaxNestingDepth()`)";
            assertEquals(expected, sce.getMessage());
        }
    }
}
