package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportingTest {

    private static final String REPORT_HEADER = "Bank\n" + "========\n";

    @Test
    @Disabled
    public void shouldGetReport() {
        Reporting sut = new Reporting();
        Branch rootBranch = mock(Branch.class);
        assertEquals(
                REPORT_HEADER +
                        "Branch # 1\n" +
                        "------\n" +
                        "Client # 1\n" +
                        "### Account # 1: 100\n" +
                        "### Account # 2: 200\n" +
                        "Client # 2\n" +
                        "<absent>",
                sut.getReport(rootBranch)
        );
    }

    @Test
    public void shouldReturnNotEmptyResultWhenRootBranchIsPresent() {
        Reporting sut = new Reporting();

        Branch branch = mock(Branch.class);
        String report = sut.getReport(branch);

        assertNotNull(report);
        assertNotEquals("", report);
    }

    @Test
    public void shouldReturnEmptyReportWithHeaderWhenBankHasNoBranches() {
        Reporting sut = new Reporting();

        Branch rootBranch = mock(Branch.class);
        when(rootBranch.getChildren()).thenReturn(Collections.emptyList());


        assertEquals(
                REPORT_HEADER +
                        "<absent>",
                sut.getReport(rootBranch)
        );
    }

    @Test
    public void shouldExistHeaderWhenChildBranchIsPresent() {
        Reporting sut = new Reporting();
        Branch rootBranch = mock(Branch.class);
        when(rootBranch.getChildren()).thenReturn(Collections.singletonList(mock(Branch.class)));
        assertTrue(sut.getReport(rootBranch).contains(REPORT_HEADER));
    }

    @Test
    public void shouldExistBranchHeaderWhenTwoBranches() {
        Reporting sut = new Reporting();

        Branch rootBranch = mock(Branch.class);
        when(rootBranch.getChildren()).thenReturn(List.of(
                mock(Branch.class), mock(Branch.class), mock(Branch.class)
        ));

        var report = sut.getReport(rootBranch);

        for (int i = 1; i <= 3; i++) {
            assertTrue(report.contains("Branch # " + i));
        }
    }

    @Test
    public void shouldReturnEmptyReportWithHeaderWhenChildBranchHasNoClients() {
        Reporting sut = new Reporting();

        Branch rootBranch = mock(Branch.class);
        when(rootBranch.getChildren()).thenReturn(List.of(
                mock(Branch.class), mock(Branch.class), mock(Branch.class)
        ));

        assertEquals(
                REPORT_HEADER +
                        "Branch # 1\n" +
                        "------\n" +
                        "<absent>\n" +
                        "Branch # 2\n" +
                        "------\n" +
                        "<absent>\n" +
                        "Branch # 3\n" +
                        "------\n" +
                        "<absent>",
                sut.getReport(rootBranch)
        );
    }
}