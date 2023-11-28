# DEPLOY - HOW TO:

1. Lav et repo på github, og push dit project op på den.

2. Gå ind på repo'et på github, tryk på `Settings`, find `Secrets and variables` og vælg `Actions`,
   tryk på "new repo secret"(grøn knap), skriv `DOCKERHUB_USERNAME` i `Name`. I `Secret` skriv dit docker-username(Find ved at logge ind på [Docker hub](https://hub.docker.com) og kig i højre hjørne). Tryk så på `Add secret`

3. Derefter lav endnu en repo secret, hvor `Name` er `DOCKERHUB_TOKEN` og `Secret` er din [Docker hub](https://hub.docker.com) token. 
Hvis du ikke har den skrevet ned, så lav en ny, ved at gå til `Account settings` (tryk på dit navn i Docker hub) og gå til `Security` og tryk på `New Access Token` og given den et navn og alle permissions.
Kopier token før du lukker pop-up og skrev den ned et sted. Det burde ligne noget som dette: "dckr_pat_KgFfgKC7HuEL7gXoboT7gJMhSEg" (Note: denne token er slettet, og bare for vise hvordan de ser ud)
OPS! hvis du mister din Token, så kan man ikke få den tilbage, og du skal gør dette trin igen.

4. Tilføje mappen ".github" og derinde en mappe kaldet "workflows"

5. Lave en Yaml (.yml) fil i workflows mappen, hvori workflowet ligger, altså back-end detaljer såsom build og
   hvad github skal gøre med dette workflow.

6. Laver en "Dockerfile" i root (samme sted som f.eks. ".gitignore")

7. Push dine ændringer op (workflow og dockerfile)

Gå tilbage til dit repo på github, og klik på tab'en `Actions`. Hvis alt er gjort rigtigt, så burde der være en workflow enten i gang eller færdig.
Hvis den er færdig, og der er en rød cirkel med et X i midten, betyder det at der skete en fejl. Klik på den og se hvad fejl er.
Hvis fejl siger `Error: Username and password required`, så tjek at dine `DOCKERHUB_USERNAME` og `DOCKERHUB_TOKEN` er rigtigt sat op, og kør jobet igen (Ikon'et med to pile der gå rundt i en cirkel i toppen af workflow kassen).
