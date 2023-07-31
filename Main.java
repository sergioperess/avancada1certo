import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

class MainActivity extends AppCompatActivity {

private LocationManager locationManager;
private Carro carro;
private Handler handler;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

// Criar um objeto Carro
carro = new Carro();

// Definir os valores iniciais do carro
carro.setVelocidade(0);
carro.setDistanciaPercorrida(0);

// Inicializar o LocationManager
locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

// Verificar se a permissão de localização foi concedida
if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
!= PackageManager.PERMISSION_GRANTED) {
// Caso a permissão não tenha sido concedida, solicitar ao usuário
ActivityCompat.requestPermissions(this,
new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
1);
return;
}

// Solicitar atualizações de localização em uma thread separada
Thread locationThread = new Thread(new Runnable() {
@Override
public void run() {
Looper.prepare(); // Preparar o Looper para a thread

locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

Looper.loop(); // Iniciar o loop do Looper
}
});
locationThread.start(); // Iniciar a thread de localização

// Inicializar o handler para exibir os valores atualizados
handler = new Handler(Looper.getMainLooper());
}

private final LocationListener locationListener = new LocationListener() {
@Override
public void onLocationChanged(Location location) {
// Ler os dados de localização em uma thread separada
Thread updateThread = new Thread(new Runnable() {
@Override
public void run() {
// Ler os dados de localização
Localizacao localizacao = new Localizacao(location.getLatitude(), location.getLongitude(),
location.getSpeed(), location.getAccuracy());

// Atualizar os valores do carro com base na localização
carro.atualizarCarro(localizacao);

// Exibir os valores atualizados no handler principal
handler.post(new Runnable() {
@Override
public void run() {
exibirValores(carro);
}
});
}
});
updateThread.start(); // Iniciar a thread de atualização
}

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
}

@Override
public void onProviderEnabled(String provider) {
}

@Override
public void onProviderDisabled(String provider) {
}
};

private void exibirValores(Carro carro) {
// Método para exibir os valores atualizados do carro (pode ser implementado conforme necessário)
// Por exemplo:
Toast.makeText(this, "Velocidade: " + carro.getVelocidade() + " km/h" +
"\nDistância Percorrida: " + carro.getDistanciaPercorrida() + " km", Toast.LENGTH_SHORT).show();
}
}
