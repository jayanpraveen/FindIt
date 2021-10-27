#!/bin/bash
java -jar stub-runner.jar \
	--stubrunner.ids=me.realpraveen:book_detail_service:+:8081,me.realpraveen:user_service:+:8082 \
	--stubrunner.stubs-mode="local" \
        --stubrunner.workOffline=true

