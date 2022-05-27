FROM python:3.10-slim-buster

WORKDIR /home/app

COPY requirements.txt requirements.txt
COPY thesaurus_full.txt thesaurus_full.txt
RUN pip3 install -r requirements.txt

COPY src src
COPY app.py app.py

EXPOSE 8187
ENTRYPOINT ["python", "-m", "flask", "run", "--host=0.0.0.0", "--port=8187"]