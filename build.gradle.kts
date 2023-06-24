plugins {
    id("java")
    id("war")
}

group = "com.agorafob"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.apache.tomcat:tomcat-jdbc:11.0.0-M7")
    implementation("org.eclipse.jetty:apache-jstl:11.0.0")
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}