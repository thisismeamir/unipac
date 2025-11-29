#include <filesystem>
#include <fstream>
#include <iostream>

void initialize() {
    const char *homeDir =getenv("HOME");
    if (homeDir == nullptr) {
        std::cerr << "Error: HOME environment variable not set." << std::endl;
        return;
    }

    std::string unipacDir = std::string(homeDir) + "/.unipac";

    // Check if the directory exists
    if (!std::filesystem::exists(unipacDir)) {
        // Create the directory
        try {
            std::filesystem::create_directory(unipacDir);
            std::cout << "Initialized unipac at: " << unipacDir << std::endl;
            std::cout << "Making package, repo, and universe directories..." << std::endl;
            std::filesystem::create_directory(unipacDir + "/packages");
            std::filesystem::create_directory(unipacDir + "/repositories");
            std::filesystem::create_directory(unipacDir + "/universes");
            // Create unipac.conf file
            std::ofstream configFile(unipacDir + "/unipac.conf");
            if (configFile.is_open()) {
                configFile << "# unipac configuration file\n";
                configFile << "unipac {\n";
                configFile << "}\n";
                configFile.close();
                std::cout << "Created unipac.conf configuration file." << std::endl;
            } else {
                std::cerr << "Error creating unipac.conf file." << std::endl;
            }
            std::cout << "unipac initialization complete." << std::endl;
        } catch (const std::filesystem::filesystem_error &e) {
            std::cerr << "Error initializing unipac: " << e.what() << std::endl;
        }
    } else {
        std::cout << "Unipac is already initialized. You can find the base at: " << unipacDir << std::endl;
    }
}

void printFlag() {
    std::cout << R"(
             _
            (_)
 _   _ _ __  _ _ __   __ _  ___
| | | | '_ \| | '_ \ / _` |/ __|
| |_| | | | | | |_) | (_| | (__
 \__,_|_| |_|_| .__/ \__,_|\___|
              | |
              |_|
)" << '\n';
}

int main() {
    printFlag();
    // Check for /home/<user-name>/.unipac directory existence, if not present create it
    initialize();

    return 0;
}