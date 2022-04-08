
from vosk import Model, KaldiRecognizer
import sys
import os
import wave
import json

# Problem: Many files are not supported

if not os.path.exists("model"):
    print ("Please download the model from https://alphacephei.com/vosk/models and unpack as 'model' in the current folder.")
    exit (1)

AUDIO_FILE = "../resources/harvard.wav"

wf = wave.open(AUDIO_FILE, "rb")
if wf.getnchannels() != 1 or wf.getsampwidth() != 2 or wf.getcomptype() != "NONE":
    print("Audio file must be WAV format mono PCM.")
    exit(1)

model = Model("model")

# You can also specify the possible word or phrase list as JSON list, the order doesn't have to be strict
#
rec = KaldiRecognizer(model, wf.getframerate())

while True:
    data = wf.readframes(4000)
    if len(data) == 0:
        break
    if rec.AcceptWaveform(data):
        res = json.loads(rec.Result())
        print(res['text'])

print("-----")
res = json.loads(rec.FinalResult())
print(res['text'])
