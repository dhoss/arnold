DIR="${BASH_SOURCE%/*}"
if [[ ! -d "$DIR" ]]; then DIR="$PWD"; fi
. "$DIR/arnold_env.sh"
echo "${JDBC_USER}"
mvn clean install && java -jar target/Bot-1.0-uber.jar
