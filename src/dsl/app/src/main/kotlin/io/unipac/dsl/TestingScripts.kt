package io.unipac.dsl
import io.unipac.dsl.models.*
import io.unipac.dsl.models.repository.RepositoryType
import io.unipac.dsl.models.repository.repository
import io.unipac.dsl.models.universe.universe


val archRepository = repository("arch-official") {
    version = "2024.01"
    description = "Official Arch Linux repository"
    group = RepositoryType.DISTRO
    repositoryIdentifier = "arch-core"

    mirrors(
        "https://mirrors.kernel.org/archlinux/\$repo/os/\$arch",
        "https://mirror.rackspace.com/archlinux/\$repo/os/\$arch"
    )

    metadata(
        mapOf(
            "architecture" to "x86_64",
            "distribution" to "arch"
        )
    )

    beforeFetch {
        println("Syncing Arch repository...")
    }

    afterFetch {
        println("Arch repository synced successfully")
    }

    onSearch { query ->
        // Implementation for searching packages
        println("Searching for: $query")
        emptyList()
    }

    onFetchPackage { query ->
        // Implementation for fetching packages
        println("Fetching package: $query")
        emptyList()
    }

    onGetPackage { identifier ->
        // Implementation for getting specific package
        println("Getting package: $identifier")
        null
    }
}

val debianRepository = repository("debian-stable") {
    version = "12.0"
    description = "Debian Stable (Bookworm)"
    group = RepositoryType.DISTRO

    mirror("http://deb.debian.org/debian")
    mirror("http://ftp.us.debian.org/debian")

    metadata("distribution", "bookworm")
    metadata("components", "main,contrib,non-free")

    credentials {
        username = "user"
        password = "pass"
    }
}

val pipRepository = repository("pypi") {
    version = "1.0"
    description = "Python Package Index"
    group = RepositoryType.LANGUAGE

    mirror("https://pypi.org/simple")

    metadata(
        mapOf(
            "language" to "python",
            "package-format" to "wheel"
        )
    )
}

// ============================================================================
// EXAMPLE 2: Defining Individual Universes
// ============================================================================

val pythonDevUniverse = universe("python-dev") {
    description = "Python development environment"

    // Add packages with version constraints
    pkg("python", "3.11.*")
    pkg("python-pip", "latest")
    pkg("python-virtualenv", ">=20.0.0")
    pkg("git", "2.40+")

    // Or add multiple at once
    packages("gcc", "make", "cmake")

    // Reference existing repositories
    repository(pipRepository)
    repository(archRepository)

    // Set environment variables
    env("PYTHON_HOME", "/unipac/universes/python-dev")
    env("PATH", "\$PYTHON_HOME/bin:\$PATH")
    env("PYTHONPATH", "\$PYTHON_HOME/lib/python3.11/site-packages")

    // Add activation hooks
    onActivate {
        println("Activating python-dev universe...")
        println("Python 3.11 environment ready")
    }

    onDeactivate {
        println("Deactivating python-dev universe...")
    }
}

val nodeDevUniverse = universe("node-dev") {
    description = "Node.js development environment"

    pkg("nodejs", "20.x")
    pkg("npm", "latest")
    pkg("yarn", "1.22.*", optional = true)

    env(
        mapOf(
            "NODE_HOME" to "/unipac/universes/node-dev",
            "PATH" to "\$NODE_HOME/bin:\$PATH"
        )
    )

    onActivate {
        println("Node.js environment activated")
    }
}

// ============================================================================
// EXAMPLE 3: Complete Configuration with Multiple Repos and Universes
// ============================================================================

val config = unipac {

    // Define repositories
    repository("arch-official") {
        version = "2024.01"
        description = "Official Arch Linux repository"
        group = RepositoryType.DISTRO

        mirrors(
            "https://mirrors.kernel.org/archlinux/\$repo/os/\$arch",
            "https://geo.mirror.pkgbuild.com/\$repo/os/\$arch"
        )

        metadata("architecture", "x86_64")
    }

    repository("cargo") {
        version = "1.0"
        description = "Rust package registry"
        group = RepositoryType.LANGUAGE

        mirror("https://crates.io")

        metadata(mapOf(
            "language" to "rust",
            "registry" to "crates.io"
        ))

        onSearch { query ->
            println("Searching crates.io for: $query")
            emptyList()
        }
    }

    repository("local-builds") {
        description = "Local build cache"
        group = RepositoryType.LOCAL

        mirror("file:///home/user/.unipac/local")

        beforeFetch {
            println("Checking local cache...")
        }
    }

    // Define universes
    universe("ml-research") {
        description = "Machine learning research environment"

        pkg("python", "3.11.*")
        pkg("pytorch", "2.1.*")
        pkg("tensorflow", "2.14.*")
        pkg("jupyter", "latest")
        pkg("cuda", "12.0", optional = true)

        env("CUDA_HOME", "/unipac/universes/ml-research/cuda")
        env("LD_LIBRARY_PATH", "\$CUDA_HOME/lib64:\$LD_LIBRARY_PATH")

        onActivate {
            println("ML research environment activated")
            println("GPU support: ${System.getenv("CUDA_HOME") != null}")
        }
    }

    universe("web-dev") {
        description = "Full-stack web development"

        packages("nodejs", "postgresql", "redis")
        pkg("nginx", "1.24.*")

        env(mapOf(
            "DATABASE_URL" to "postgresql://localhost:5432/dev",
            "REDIS_URL" to "redis://localhost:6379"
        ))

        onActivate {
            println("Starting development services...")
        }

        onDeactivate {
            println("Stopping development services...")
        }
    }

    universe("minimal") {
        description = "Minimal system utilities"

        packages("git", "curl", "wget", "vim")

        onActivate {
            println("Minimal universe ready")
        }
    }
}

