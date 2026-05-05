package cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.*;

class CliTest {
    private Cli cli;
    private CommandLine cmd;
    private String URL = "http://localhost:9999";

    @BeforeEach
    void setUp() {
        cli = new Cli();
        cmd = new CommandLine(cli);
    }

    @Test
    void testCommandListExist() {
        int exitCode = cmd.execute("list");
        assertEquals(0, exitCode);
    }

    @Test
    void testUnknownCommand() {
        int exitCode = cmd.execute("unknown");
        // Exit code 2 signifies incorrect arguments.
        assertEquals(2, exitCode);
    }

    @Test
    void testCommandRequired() {
        int exitCode = cmd.execute();
        // Exit code 2 signifies incorrect arguments.
        assertEquals(2, exitCode);
    }

    @Test
    void testUrlOptionWithoutCommand() {
        cmd.execute("--url", URL);
        assertEquals(URL, cli.getUrl());
    }

    @Test
    void testUrlOptionBeforeCommand() {
        cmd.execute("--url", URL, "list");
        assertEquals(URL, cli.getUrl());
    }

    @Test
    void testUrlOptionAfterCommand() {
        cmd.execute("list", "--url", URL);
        assertEquals(URL, cli.getUrl());
    }
}