//
// Created by kid-a on 11/29/25.
//

#ifndef UNIPAC_PACKAGE_H
#define UNIPAC_PACKAGE_H
#include <string>

#include "../repository/repository.h"

namespace unipac::core {
    class Package {
    private:
        std::string name_;
        std::string version_;
        std::string description_;
        std::vector<repository::Repository> availableInRepositories_;
        std::string license_;
        std::vector<core::Package> dependencies_;

    public:
        // Constructor
        Package(const std::string &name, const std::string &version, const std::string &description,
                const std::string &repository);

        // Getters
        std::string getName() {
            return name_;
        };

        std::string getVersion() {
            return version_;
        };

        std::string getDescription() {
            return description_;
        };

        std::vector<repository::Repository> getAvailableInRepositories() {
            return availableInRepositories_;
        };
    };
} // namespace unipac::core

#endif //UNIPAC_PACKAGE_H