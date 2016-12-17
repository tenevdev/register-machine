#!/usr/bin/env bash
mvn clean jacoco:prepare-agent test jacoco:report checkstyle:checkstyle checkstyle:check
