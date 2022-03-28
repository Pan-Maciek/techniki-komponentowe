import PyPDF2
import re
from typing import List
import pdftotext

files: List[str] = [
    "D:/tmp/siatki-2018.pdf",
    "D:/tmp/KeepTalkingAndNobodyExplodes-BombDefusalManual-v2-pl.pdf",
    "D:/tmp/lab2.pdf"
]

# Using PyPDF2 - I tried for 3 files and each time I don't get any text extracted
for file in files:
    pdf: PyPDF2.PdfFileReader = PyPDF2.PdfFileReader(file);
    phrase: str = "matematyka"

    for i in range(0, pdf.getNumPages()):
        pdfPage = pdf.getPage(i)
        print(f"On page {i + 1}")
        text = pdfPage.extractText()
        print(text)
        searchRes = re.search(phrase, text)
        print(searchRes)

# Using pdftotext - it is suppossed to work much better but I found it difficult to install the package on Windows
for file in files:
    phrase: str = "matematyka"

    pdf = pdftotext.PDF(file)

    for page in pdf:
        print(page)
