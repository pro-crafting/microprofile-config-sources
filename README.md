# record-jar-converter
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pro-crafting.tools/microprofile-config-sources/badge.svg)](https://maven-badges.herokuapp.com/maven-central/microprofile-config-sources/microprofile-config-sources)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This project add additional config sources for microprofile-config 1.3.

## Available config sources
* docker secret - com.pro_crafting.tools.microprofile_config_sources.DockerSecretSource - Reads secrets created by docker swarm from the /run/secrets directory, and makes them available under the secret name as configuration value

## Usage:
"Custom ConfigSource will get picked up via the {@link java.util.ServiceLoader} mechanism and and can be registered by
providing a file
"META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource"
which contains the fully qualified {@code ConfigSource} implementation class name as content."
 
## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/Postremus/record-jar-converter/tags).

## Authors

* **Martin Panzer** - *Initial work* - [Postremus](https://github.com/Postremus)

See also the list of [contributors](https://github.com/Postremus/record-jar-converter/contributors) who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* All the people behind [Maven](https://maven.apache.org/team-list.html) and [Java](https://java.net/people).