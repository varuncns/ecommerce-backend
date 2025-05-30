package com.ecommerce.service;

import com.ecommerce.dto.FinancialInsightsDTO;
import com.ecommerce.dto.LogisticsDashboardDTO;

public interface AdminDashboardService {
    LogisticsDashboardDTO getLogisticsSummary();
    FinancialInsightsDTO getFinancialInsights();
}
