# Desafio LuizaLabs (Netshoes)
Projeto que realiza chamadas a API [https://api.github.com/gists/public] e converte a resposta da API para uma lista de gists para o usuário dentro do APP.

# Arquitetura
Foi optado por utilizar o padrão MVP nesse projeto, assim como os príncipios do Clean Architecture. Em virtude do conhecimento de que app da Netshoes utiliza essa arquitetura no momento. 

![image](https://github.com/yagosouza/android-test-luizalabs/assets/3721436/452d1196-5149-473f-822c-8f5709a4a112)

# O que foi utilizado

Conceitos de arquitetura/código limpo/padrões
- Clean Architecture
- MVP
- SOLID
- Padrão repositório

Android
- Componentes do Jetpack
- Presenter
- XML
- Coroutines
- Flow
- Retrofit
- Room
- Flavors

Framework DI
- Koin

Testes unitários
- MockK
- CoroutineTestRule

# Demonstração
<img src="https://github.com/yagosouza/android-test-luizalabs/assets/3721436/0e77d29f-56f2-45fb-a37e-45535adc7994" alt="demo" width="300"/>

# Futuro
Como o aplicativo ainda pode ser melhorados, tem alguns pontos que podem e já foram analisados e pensados:
- Modularizar
- Nova feature de Detalhamento do conteúdo do arquivo em Detalhes
- Botão para chegar ao top da lista e um swipe refresh
- Entre outros
