class Carro {
private double velocidade;
private double distanciaPercorrida;

public Carro() {
velocidade = 0;
distanciaPercorrida = 0;
}

public void setVelocidade(double velocidade) {
this.velocidade = velocidade;
}

public void setDistanciaPercorrida(double distanciaPercorrida) {
this.distanciaPercorrida = distanciaPercorrida;
}

public void atualizarCarro(Localizacao localizacao) {
// Reconciliar dados com base na localização mais recente
double novaVelocidade = localizacao.getVelocidade();
double novaDistanciaPercorrida = localizacao.getDistanciaPercorrida();

// Apenas atualizar se os novos dados forem mais recentes e válidos
if (novaVelocidade > 0 && novaDistanciaPercorrida > distanciaPercorrida) {
setVelocidade(novaVelocidade);
setDistanciaPercorrida(novaDistanciaPercorrida);
}
}

public double getVelocidade() {
return velocidade;
}

public double getDistanciaPercorrida() {
return distanciaPercorrida;
}
}