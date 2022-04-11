#### Znalezione technologie:
- https://alphacephei.com/vosk/ - działa offline, ale lekkie modele nie są zbyt dokładne, więc wymagałoby pobierania sporych paczek z modelami
- https://github.com/cmusphinx/pocketsphinx - nei był zbyt wygodny do skonfigurowania, ale można go rozważyć
- https://pypi.org/project/SpeechRecognition/ - Moj faworyt - Wiele źródeł, z których można korzystać, w tym `google_recognition`, który nie wymaga generowania klucza do tego daje naprawdę dobre efekty

### Poniżej opisy przy zastosowaniu `SpeechRecognition`

#### Przykłady
- sr-example.py: przykład podstawowego użycia `SpeechRecognition`
- sr-for-long-files.py: przykład (znaleziony w internecie) użycia `SpeechRecognition` z rozbiciem pliku na mniejsze (potrzebny `pydub` (który zaś rzekomo wymaga `ffmpeg` lub `avconv` - ale wydaje się działaś nawet bez nich)) - niestety tworzy on pliki (docelowo trzeba by dodać czyszczenie tymczasowo tworzonych plików częściowych)

#### Problemy
- Rozpoznawanie dźwięku trwa stosunkowo długo (np. 6-17s dla pliku trwającego 50s - ok. 1/3)
- Niektóre pliki w ogóle "nie dają się przeanalizować": dla `what-a-wonderful-world.wav` zwracany jest tylko pierwszy wers, 
dla `africa-toto.wav` jest jedynie rzucany error (Google Speech Recognition could no understand audio) - prawdopodobnie jest to spowodowane instrumentami, które można próbować wyciąć Transformatą Fouriera,
 jednak brzmi to, jak sporo roboty
- Wymaga połączenia z internetem

#### Potencjalne szczegóły implementacyjne
Chcąc podać kontekst (np. czas, w okolice minuty, w której występuje dana fraza) można rozbić plik na wiele mniejszych plików i w ten sposób określić okolicę czasu, w którym pojawiła się fraza

#### Opis przykładowych plików z `/resources`:
- `africa-toto.wav` - plik muzyczny, dla którego rzucane są errory
- `harvard.wav`, `male.wav` - pliki, w których mężczyzna wypowiada słowa - dobrze sobie z nimi radzi speech_recognition
- `jackhammer.wav` - przemowa zagłuszona przez hałas - dobry to testowania metody `adjust_for_ambient_noise`
- `mp3-file.mp3` - plik .mp3
- `what-a-wonderful-world.wav` - piosenka, dla której speech_recognition rozpoznaje wyłącznie pierwszy wers
- `wma-file.wma` - plik. wma
