# App Livrou
### Com o desenvolvimento deste app pude aplicar conceitos fundamentais do desenvolvimento Android, tais como:

* Consumo de API's externas
* Execução de tarefas em segundo plano
* Persistência de dados
* Criação e manipulação de Fragments e integração destes com suas respectivas activities
* Utilização de bibliotecas de terceiros para manipulação de imagens
* Integração com o Material Design para adequação do layout aos padrões utilizados
* Conceitos gerais de navegação
* Utilização da arquitetura MVVM para estruturar o projeto garantindo a separação de conceitos conforme recomendado pela documentação oficial

### Para elaboração deste app eu consumo a API do Google Books e salvo os dados obtidos internamente com o SQLite para que os mesmos não sejam perdidos caso os componentes de UI sejam recriados ou caso o SO remova os dados em cache.

### Utilizo RecyclerView juntamente com GridLayout para exibição das informações, além do TabLyout e ViewPager para permitir a navegação entre as categorias disponíveis.

<p align="center">
 <img src="/files_for_presentation/video_3.gif" alt="" height="500">
 <img src="/files_for_presentation/video_2.gif" alt="" height="500">
 </br>
 <img src="/files_for_presentation/video_4.gif" alt="" height="500">
 <img src="/files_for_presentation/video_1.gif" alt="" height="500">
</p>

### Abaixo vê-se um diagrama de classes simplificado, mostrando a visão geral do sistema:

<p align="center">
 <img src="/files_for_presentation/Class Diagram6.jpg" alt="Class Diagram" width="1000" height="1300">
</p>

### Pontos a melhorar:

* Configurar variáveis ambiente para evitar de compartilhar a chave da API
* Mais refinamentos tanto de interface quanto de práticas de codifição
* Utilização de linters
