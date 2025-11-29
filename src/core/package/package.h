//
// Created by kid-a on 11/29/25.
//

#ifndef UNIPAC_PACKAGE_H
#define UNIPAC_PACKAGE_H
#include <string>
#include <utility>

#include "../repository/repository.h"


namespace unipac::core {
struct PackageMetadata {
    // Basic metadata about a package
    std::string name;
    std::string version;
    std::string description;
    std::string license;

    // Repositories where the package is available
    std::vector<core::RepositoryMetadata> availableInRepositories;
    // Dependencies of the package
    std::vector<core::PackageMetadata> dependencies;
};



    class Package {
    private:
        core::PackageMetadata metadata_;
    public:
        // Constructor
        explicit Package(core::PackageMetadata metadata) : metadata_(std::move(metadata)) {

        }
        // Getters
        core::PackageMetadata getMetadata() const {
            return metadata_;
        }



    };
} // namespace unipac::core

#endif //UNIPAC_PACKAGE_H