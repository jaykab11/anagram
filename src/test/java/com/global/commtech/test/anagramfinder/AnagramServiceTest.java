package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(args = {"src/test/resources/example2.txt"})
@ExtendWith(OutputCaptureExtension.class)
class AnagramServiceTest {

    @Autowired
    AnagramService anagramService;

    @Test
    void shouldFindAnagrams() throws IOException {
        String output=anagramService.outputResult(new File("src/test/resources/example2.txt"));
        String expectedOutput="abc,bca\nnhu\nkir\npoulin,pinlou\n";

        assertThat(output).isEqualTo(expectedOutput);

    }




}