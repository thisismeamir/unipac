#ifndef UNIPAC_REPOSITORY_H
#define UNIPAC_REPOSITORY_H
#include <string>
#include <vector>


namespace unipac::core {
    class Package;
}

namespace unipac::core::repository {
    class PackageUniqueIdentifier;

    class Repository {
        // Name of the repository such as AUR, apt, pacman, etc.
    private:
        std::string name_;
        std::string abbreviation_;
        std::string description_;

    public:
        // Constructor
        Repository(const std::string &name, const std::string &abbreviation, const std::string &description);

        // Getters
        std::string getName() const;
        std::string getAbbreviation() const;
        std::string getDescription() const;

        // Setters
        void setName(const std::string &name);

        void setAbbreviation(const std::string &abbreviation);

        void setDescription(const std::string &description);

        // Other methods
        // Search for packages in the repository
        std::vector<core::Package> search(const std::string &query);
        core::Package getPackage(const core::repository::PackageUniqueIdentifier &identifier);
    };

    class PackageUniqueIdentifier {
    private:
        std::string repositoryName_;
        std::string packageName_;
        std::string version_;

    public:
        // Constructor
        PackageUniqueIdentifier(const std::string &repository_name, const std::string &package_name,
                                const std::string &version);
        // Getters
        std::string getRepositoryName() {
            return repositoryName_;
        };
        std::string getPackageName() {
            return packageName_;
        };
        std::string getVersion() {
            return version_;
        };

        // Setters
        void setRepositoryName(const std::string &repository_name) {

        };
        void setPackageName(const std::string &package_name);
        void setVersion(const std::string &version);
    };
};
#endif //UNIPAC_REPOSITORY_H
