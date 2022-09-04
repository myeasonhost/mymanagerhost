FROM openjdk:8
MAINTAINER docker_maven mydoctorhost@email.com
WORKDIR /ROOT
CMD ["java", "-version"]
ENTRYPOINT ["java", "-jar", "${project.build.finalName}.jar","--spring.profiles.active=prod"]
