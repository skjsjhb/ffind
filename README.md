# Forge Library Finder

Finds Forge libraries used in legacy (1.5.1 and earlier) versions.

## What's This?

Forge downloads extra libraries in legacy versions from <https://files.minecraftforge.net/fmllibs>, which is now dead.

This tool digs into the client jar file, finds out those libraries and prints them.
Making it possible to be fetched from other sources.

## Usage

This module has been integrated into [Alicorn](https://github.com/Andy-K-Sparklight/Alicorn).
It supports these legacy versions out of box.

However, it's also possible to use this tool without Alicorn:

1. Installs the game and Forge manually.
2. Locates the client jar.
3. Runs this library together with the client:

    ```shell
    java -cp "/path/to/client.jar:/path/to/ffind.jar" moe.skjsjhb.ffind.Main
    ```

   It should print the libraries found.

## License

[GPL-3.0 or later](./LICENSE)
