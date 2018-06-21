/*
 * Copyright 2016-2018. the original author or authors.
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

package org.springframework.cloud.appbroker.serviceinstance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.cloud.appbroker.deployer.BackingAppProperties;
import org.springframework.cloud.appbroker.deployer.BackingAppDeploymentService;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProvisionServiceInstanceWorkflowTest {

	private BackingAppProperties backingAppProperties;
	@Mock
	private BackingAppDeploymentService backingAppDeploymentService;
	private ProvisionServiceInstanceWorkflow provisionServiceInstanceWorkflow;

	@Test
	void shouldProvisionDefaultServiceInstance() {
		// given that properties contains app name
		backingAppProperties = new BackingAppProperties("helloworldapp", "http://myfiles/app.jar");
		provisionServiceInstanceWorkflow = new ProvisionServiceInstanceWorkflow(backingAppProperties, backingAppDeploymentService);

		// when
		provisionServiceInstanceWorkflow.provision();

		//then deployer should be called with the application name
		verify(backingAppDeploymentService, times(1))
			.execute(backingAppProperties);
	}
}