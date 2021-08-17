package io.jenkins.plugins.pipeline.steps;

import java.io.Serializable;
import java.util.Set;

import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import com.google.common.collect.ImmutableSet;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.Run;
import hudson.model.TaskListener;
import io.jenkins.plugins.pipeline.steps.executions.DevOpsConfigGetSnapshotsStepExecution;
import io.jenkins.plugins.utils.DevOpsConstants;


public class DevOpsConfigGetSnapshotsStep extends Step implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean m_enabled;
    private boolean m_ignoreErrors;
    private String applicationName;
    private String deployableName;
    private String changeSetId;

    @DataBoundConstructor
    public DevOpsConfigGetSnapshotsStep(String applicationName, String deployableName, String changeSetId) {
        m_enabled = true;
        m_ignoreErrors = false;
        this.applicationName = applicationName;
        this.deployableName = deployableName;
        this.changeSetId = changeSetId;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new DevOpsConfigGetSnapshotsStepExecution(context, this);
    }

    public boolean isEnabled() {
        return m_enabled;
    }

    @DataBoundSetter
    public void setEnabled(boolean enabled) {
        this.m_enabled = enabled;
    }

    public boolean isIgnoreErrors() {
        return m_ignoreErrors;
    }

    @DataBoundSetter
    public void setIgnoreErrors(boolean ignore) {
        this.m_ignoreErrors = ignore;
    }

    @DataBoundSetter
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setDeployableName(String deployableName) {
        this.deployableName = deployableName;
    }

    public String getDeployableName() {
        return deployableName;
    }

	public String getChangeSetId() {
		return changeSetId;
	}

	public void setChangeSetId(String changeSetId) {
		this.changeSetId = changeSetId;
	}

    @Extension
	public static class DescriptorImpl extends StepDescriptor {

		@Override
		public String getFunctionName() {
			return DevOpsConstants.GET_SNAPSHOTS_STEP_FUNCTION_NAME.toString();
		}

		@Override
		public String getDisplayName() {
			return DevOpsConstants.GET_SNAPSHOTS_STEP_DISPLAY_NAME.toString();
		}

		@Override
		public Set<? extends Class<?>> getRequiredContext() {
			return ImmutableSet.of(Run.class, TaskListener.class, EnvVars.class);
		}

	}
    
}