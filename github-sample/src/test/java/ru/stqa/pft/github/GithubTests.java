package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("16435fb9ef2ac6e905657cd6224f5b43f6776dcf");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("trynadcatiy", "java_pft")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build()))
            System.out.println(new RepoCommit.Smart(commit).message());
    }
}
