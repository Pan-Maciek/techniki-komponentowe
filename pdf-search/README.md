##### Wiele z dostępnych bibliotek jest płatnych (z opcją free-trialu, ale to może być niezbyt wygodna opcja):
 - https://www.datalogics.com/products/pdf-sdks/adobe-pdf-library/ https://www.pdfa.org/product/adobe-pdf-library/
 - https://www.idrsolutions.com/jpedal/
 - https://developers.foxit.com/
 - https://pspdfkit.com/guides/java/

##### Da się jednak znaleźć również kilka darmowych rozwiązań:
- (Java) https://pdfbox.apache.org/
- (Java) https://tika.apache.org/ (podobno wrapper na apache pdfbox, który jest powyżej - wydaje się działać bardzo sensownie)
- (Python) https://pythonhosted.org/PyPDF2/ (Przetestowałem, ale nie dla wielu plików testowych nie potrafił nic wyszukać - ludzie w internecie zgłaszali podobne problemy)
- (Python) https://pypi.org/project/pdftotext/ (Podobno OOTB działa lepiej niż PyPDF2, ale instalacja na Windowsie to męka - głównie dlatego bym odradzał, ale nie jest to coś, czego nie ma sensu rozważać)

##### Podsumowanie
Najsensowniej ze znalezionych przeze mnie rozwiązań wydawał się działać Apache Tika do Javy (przykłady użycia w pdf-search-java)
Po decyzji o sprawdzeniu funkcjonowania tej biblioteki w Scali okazało się, że ma ona pewne problemy, jednak Apache pdfbox,
 na którego Tika była wrapperem sprawdził się bardzo dobrze i myślę, że można pójść w tym kierunku
