package cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

import java.util.concurrent.Callable;

@Command(name = "list", description = "List the available products")
public class ListCommand  implements Callable<Integer> {
    // The parent command to access the "global" options,
    // i.e. options that are shared across all subcommands.
    @ParentCommand
    private Cli parentCommand;

    @Override
    public Integer call() throws Exception {
        IO.println(String.format("list -- URL: %s", parentCommand.getUrl()));
        // TODO: logic of listing the products
        return 0;
    }
}
