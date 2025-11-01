package com.learning.multimodel.config;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class LocalTimeTool {

    @Tool(name = "getLocalTime", description = "Get the current local time")
    public String  getLocalTime() {
        return java.time.LocalTime.now().toString();
    }

    @Tool(name = "getLocalTimeFromZone", description = "Get the current local time")
    public String  getLocalTimeFromZone(@ToolParam( description = "The timezone to get the local time for") String timezone) {
        return java.time.LocalTime.now(ZoneId.of(timezone)).toString();
    }
}
