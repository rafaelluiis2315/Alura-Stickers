import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer umn conexao Http e buscar os top filmes
        
/*         String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();  */

        
        String url = "https://api.nasa.gov/planetary/apod?api_key=Zb2Q1xSHqzuXJq9ZnzXnqnD6qNkDfByuvwYVJyhh&start_date=2022-06-22&end_date=2022-07-22";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
         

        clienteHttp clienteHttp = new clienteHttp();
        String json = clienteHttp.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
