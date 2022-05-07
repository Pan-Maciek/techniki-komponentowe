#!/bin/bash
# Fail on first failed test
set -e

# Start the first process
newman run odt-search.json &&
newman run pdf-search.json


# Wait for all processes to exit
wait