import speech_recognition as sr
import time
AUDIO_FILE = "../resources/male.wav"

# Nice tut: https://realpython.com/python-speech-recognition/

r = sr.Recognizer()

start = time.time()
with sr.AudioFile(AUDIO_FILE) as source:
    # TODO: Can be commented out to check how it behaves, especially for files with noise
    r.adjust_for_ambient_noise(source, duration=0.5)
    audio = r.record(source)

try:
    # SpeechRecognition dostarcza kilka api (m.in. google, google cloud, ibm, bing) - plusem google'a jest to, że jako jedyny nie wymaga klucza do api
    print(r.recognize_google(audio, key=None))
except sr.UnknownValueError:
    print("Google Speech Recognition could not understand audio")
except sr.RequestError as e:
    print("Could not request results from Google Speech Recognition service; {0}".format(e))

end = time.time()
print(end-start)
