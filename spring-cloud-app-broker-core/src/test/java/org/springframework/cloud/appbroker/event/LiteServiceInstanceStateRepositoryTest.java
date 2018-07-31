/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.appbroker.event;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import org.springframework.cloud.servicebroker.model.instance.OperationState;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LiteServiceInstanceStateRepositoryTest {

	private LiteServiceInstanceStateRepository stateRepository;

	@BeforeEach
	void setUp() {
		this.stateRepository = new LiteServiceInstanceStateRepository();
	}

	@Test
	void saveAndGet() {
		StepVerifier.create(stateRepository.saveState("foo", OperationState.IN_PROGRESS, "bar"))
			.consumeNextWith(serviceInstanceState -> {
				assertThat(serviceInstanceState.getOperationState()).isEqualTo(OperationState.IN_PROGRESS);
				assertThat(serviceInstanceState.getDescription()).isEqualTo("bar");
			})
			.verifyComplete();

		StepVerifier.create(stateRepository.getState("foo"))
			.consumeNextWith(serviceInstanceState -> {
				assertThat(serviceInstanceState.getOperationState()).isEqualTo(OperationState.IN_PROGRESS);
				assertThat(serviceInstanceState.getDescription()).isEqualTo("bar");
			})
			.verifyComplete();
	}

}