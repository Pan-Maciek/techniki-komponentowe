#!/bin/bash
# Fail on first failed test
set -e

# Start the first process
newman run collections/odt-search.json &&
newman run collections/pdf-search.json &&
newman run collections/text-search.json &&
newman run collections/audio-search.json &&
newman run collections/translation.json



# Wait for all processes to exit
wait