class Localizacao {
private double latitude;
private double longitude;
private double velocidade;
private double precisao;

public Localizacao(double latitude, double longitude, double velocidade, double precisao) {
this.latitude = latitude;
this.longitude = longitude;
this.velocidade = velocidade;
this.precisao = precisao;
}

public double getLatitude() {
return latitude;
}

public double getLongitude() {
return longitude;
}

public double getVelocidade() {
return velocidade;
}

public double getDistanciaPercorrida() {
return 10.0;
}
}
