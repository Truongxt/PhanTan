plugins {
    id("java")
}

group = "iuh.fit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))).forEach { file ->
        implementation(files(file))
    }
    implementation ("org.hibernate:hibernate-core:7.0.0.Beta1")
    implementation ("org.glassfish.jaxb:jaxb-runtime:4.0.5")
    // https://mvnrepository.com/artifact/net.datafaker/datafaker
    implementation("net.datafaker:datafaker:2.4.2")





}

tasks.test {
    useJUnitPlatform()
}