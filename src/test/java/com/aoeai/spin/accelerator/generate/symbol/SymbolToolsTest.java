package com.aoeai.spin.accelerator.generate.symbol;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SymbolToolsTest {

    @Test
    public void findSymbolTest() throws IOException {
        SymbolTools.findSymbol(new File("/Users/aoe/github/study/Java/book-modern-java-in-action/src/main/java/wyyl1"),
                "java");
    }
}