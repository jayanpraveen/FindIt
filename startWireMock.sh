#!/bin/bash
java -jar stub-runner.jar \
	--stubrunner.ids=me.realpraveen:book_detail_service:+:8482 \
	--stubrunner.stubs-mode="local" \
        --stubrunner.workOffline=true

