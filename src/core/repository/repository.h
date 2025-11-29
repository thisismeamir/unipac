#ifndef UNIPAC_REPOSITORY_H
#define UNIPAC_REPOSITORY_H
#include <string>
#include <vector>


namespace unipac::core {
    struct PackageMetadata;
    class Package;
}

namespace unipac::core {
    struct RepositoryMetadata {
        // Basic metadata about a repository
        std::string name;
        std::string abbreviation;
        std::string description;
    };
    class Repository {
        // Name of the repository such as AUR, apt, pacman, etc.
    private:
        core::RepositoryMetadata metadata;

    public:
        // Constructor
        Repository(const std::string &name, const std::string &abbreviation, const std::string &description);

        // Search for packages in the repository
        std::vector<core::Package> search(const std::string &query);
        core::Package getPackage(const core::PackageMetadata &identifier);
    };
};
#endif //UNIPAC_REPOSITORY_H
