# controle-familia-backend

# Buildar Projeto
Verificar no DockerFile se a versão da JDK esta equiparada com a versao do projeto  
mvn package -DskipTests=true

# Gerar Imagem Docker
docker build --platform="linux/amd64" -f src/main/docker/Dockerfile.jvm -t marcosrossoni/home_money_manager .

# Enviar Imagem Server Docker
docker push marcosrossoni/home_money_manager