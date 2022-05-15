FROM python:3.10-slim-buster

WORKDIR /home/app

COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt
RUN apt update && apt install -y ffmpeg && rm -rf /var/lib/apt/lists/*

COPY src src
COPY app.py app.py

EXPOSE 8185
ENTRYPOINT ["python", "-m", "flask", "run", "--host=0.0.0.0", "--port=8185"]
