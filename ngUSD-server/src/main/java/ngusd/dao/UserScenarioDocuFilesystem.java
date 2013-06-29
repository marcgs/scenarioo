package ngusd.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ngusd.configuration.NgusdConfiguration;
import ngusd.docu.model.Branch;
import ngusd.docu.model.Build;
import ngusd.docu.model.Scenario;
import ngusd.docu.model.Step;
import ngusd.docu.model.UseCase;
import ngusd.rest.model.branches.BranchBuilds;
import ngusd.rest.model.branches.BuildLink;

/**
 * Loads data from the files on the filesystem that have been generated by UI
 * tests
 */
public class UserScenarioDocuFilesystem {
	
	public File rootDir;
	
	public UserScenarioDocuFilesystem() {
		// TODO read this from configuration file instead.
		this.rootDir = NgusdConfiguration.DOCU_ROOT_DIR;
	}
	
	public File filePath(final String... names) {
		File file = rootDir;
		for (String name : names) {
			file = new File(file, name);
		}
		return file;
	}
	
	public Branch loadBranch(final String branchName) {
		File file = filePath(branchName, "branch.xml");
		return XMLFileUtil.unmarshal(file, Branch.class);
	}
	
	public List<Branch> loadBranches() {
		return XMLFileUtil.unmarshalListOfFilesFromSubdirs(rootDir, "branch.xml", Branch.class);
	}
	
	public List<BuildLink> loadBuilds(final String branchName) {
		File dir = filePath(branchName);
		List<BuildLink> result = new ArrayList<BuildLink>();
		for (ObjectFromDirectory<Build> build : XMLFileUtil.unmarshalListOfFilesFromSubdirsWithDirNames(dir,
				"build.xml", Build.class)) {
			BuildLink link = new BuildLink(build.getObject(), build.getDirectoryName());
			result.add(link);
		}
		return result;
	}
	
	public Build loadBuild(final String branchName, final String buildName) {
		File file = filePath(branchName, buildName, "build.xml");
		return XMLFileUtil.unmarshal(file, Build.class);
	}
	
	public List<BranchBuilds> loadBranchBuildsList() {
		List<BranchBuilds> result = new ArrayList<BranchBuilds>();
		List<Branch> branches = loadBranches();
		for (Branch branch : branches) {
			BranchBuilds branchBuilds = new BranchBuilds();
			branchBuilds.setBranch(branch);
			branchBuilds.setBuilds(loadBuilds(branch.getName()));
			result.add(branchBuilds);
		}
		return result;
	}
	
	public List<UseCase> loadUsecases(final String branchName, final String buildName) {
		File dir = filePath(branchName, buildName);
		return XMLFileUtil.unmarshalListOfFilesFromSubdirs(dir, "usecase.xml", UseCase.class);
	}
	
	public UseCase loadUsecase(final String branchName, final String buildName, final String usecaseName) {
		File file = filePath(branchName, buildName, usecaseName, "usecase.xml");
		return XMLFileUtil.unmarshal(file, UseCase.class);
	}
	
	public List<Scenario> loadScenarios(final String branchName, final String buildName, final String usecaseName) {
		File dir = filePath(branchName, buildName, usecaseName);
		return XMLFileUtil.unmarshalListOfFilesFromSubdirs(dir, "scenario.xml", Scenario.class);
	}
	
	public Scenario loadScenario(final String branchName, final String buildName, final String usecaseName,
			final String scenarioName) {
		File file = filePath(branchName, buildName, usecaseName, scenarioName, "scenario.xml");
		return XMLFileUtil.unmarshal(file, Scenario.class);
	}
	
	public List<Step> loadSteps(final String branchName, final String buildName, final String usecaseName,
			final String scenarioName) {
		File dir = filePath(branchName, buildName, usecaseName,
				scenarioName, "steps");
		return XMLFileUtil.unmarshalListOfFiles(dir, Step.class);
		
	}
	
	public File loadScreenshot(final String branchName, final String buildName, final String usecaseName,
			final String scenarioName, final String imgName) {
		File img = filePath(branchName, buildName, usecaseName,
				scenarioName, "screenshots", imgName);
		return img;
	}
	
}
