package cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Option;
import picocli.CommandLine.ScopeType;

import java.util.concurrent.Callable;

@Command(name = "gl-vendure-cli", mixinStandardHelpOptions = true, version = "0.1.0",
        subcommands = {ListCommand.class, HelpCommand.class},
        description = "Interact with the Vendure e-commerce platform from the command line.")
public class Cli implements Callable<Integer> {
    // scope = INHERIT means this options will be available in all subcommands.
    @Option(names = {"-u", "--url"}, description = "URL to the Vendure backend.", scope = ScopeType.INHERIT)
    private String url = "http://localhost:3000";

    @Override
    public Integer call() throws Exception {
        IO.println(String.format("URl: %s", url));
        // TODO: logic of the CLI entrypoint.
        return 0;
    }

    public String getUrl() {
        return url;
    }

    static void main(String... args) {
        int exitCode = new CommandLine(new Cli()).execute(args);
        System.exit(exitCode);
    }
}
