rootProject.name = "veld"
include(":composeApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
include(":core:common")
include(":core:design-system")
include(":core:helpers")
include(":core:di")

include(":datasource:api:local")
include(":datasource:api:network")

include(":datasource:impl:network")
include(":datasource:impl:local")

include(":feature:spell:data")
include(":feature:spell:domain")
include(":feature:spell:presentation")
include(":feature:spell:ui")

include(":feature:spell-details:data")
include(":feature:spell-details:domain")
include(":feature:spell-details:presentation")
include(":feature:spell-details:ui")

include(":feature:item:presentation")
include(":feature:item:ui")

include(":feature:classes:presentation")
include(":feature:classes:ui")

include(":feature:classes-details:data")
include(":feature:classes-details:domain")
include(":feature:classes-details:presentation")
include(":feature:classes-details:ui")

include(":feature:race:presentation")
include(":feature:race:ui")

include(":feature:bestiary:data")
include(":feature:bestiary:domain")
include(":feature:bestiary:presentation")
include(":feature:bestiary:ui")

include(":feature:search:presentation")
include(":feature:search:ui")