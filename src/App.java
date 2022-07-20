import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer umn conexao Http e buscar os top filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_1r4uh79j";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair so os dados que eu quero (titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            
            // pegar infomracoes dos filmes
            String titulo = filme.get("title");
            String urlImagem = filme.get("image");
            String ranck = filme.get("imDbRating");
            
            // gerar figurinha 
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println(urlImagem);
            System.out.println(ranck);
            System.out.println();
        }
    }
}
