#!/bin/bash

echo "Environment variables:"
printenv

# Call the original entry point
exec "$@"
