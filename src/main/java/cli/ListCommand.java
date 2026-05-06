package cli;

import format.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;
import product.ProductList;

import java.util.concurrent.Callable;

@Command(name = "list", description = "List the available products")
public class ListCommand implements Callable<Integer> {
    // The parent command to access the "global" options,
    // i.e. options that are shared across all subcommands.
    @ParentCommand
    private Cli parentCommand;

    @Option(names = {"-f", "--format"},
            description = "Format of the output, one of ${COMPLETION-CANDIDATES} [Default: ${DEFAULT-VALUE}]")
    private Format format = Format.table;

    @Override
    public Integer call() throws Exception {
        String url = parentCommand.getUrl();
        IO.println(String.format("list -- URL: %s", url));

        ProductList productList = ProductList.retrieveProducts(url);
        FormatStrategy formatStrategy = getFormatStrategy();
        IO.println(formatStrategy.formatProductList(productList));

        return 0;
    }

    private FormatStrategy getFormatStrategy() {
        return switch (format) {
            case table -> new TableFormat();
            case json -> new JsonFormat();
        };
    }
}
