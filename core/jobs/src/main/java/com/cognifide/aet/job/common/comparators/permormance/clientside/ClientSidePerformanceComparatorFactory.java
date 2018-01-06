/**
 * AET
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cognifide.aet.job.common.comparators.permormance.clientside;

import com.cognifide.aet.communication.api.metadata.Comparator;
import com.cognifide.aet.job.api.comparator.ComparatorFactory;
import com.cognifide.aet.job.api.comparator.ComparatorJob;
import com.cognifide.aet.job.api.comparator.ComparatorProperties;
import com.cognifide.aet.job.api.datafilter.DataFilterJob;
import com.cognifide.aet.job.api.exceptions.ParametersException;
import com.cognifide.aet.job.common.comparators.permormance.clientside.parser.ClientSidePerformanceParser;
import com.cognifide.aet.vs.ArtifactsDAO;
import java.util.List;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

@Component
@Service
public class ClientSidePerformanceComparatorFactory implements ComparatorFactory {

  private final ClientSidePerformanceParser clientSidePerformanceParser = new ClientSidePerformanceParser();

  @Reference
  private ArtifactsDAO artifactsDAO;

  @Override
  public String getType() {
    return ClientSidePerformanceComparator.TYPE;
  }

  @Override
  public String getName() {
    return ClientSidePerformanceComparator.NAME;
  }

  @Override
  public int getRanking() {
    return 80;
  }

  @Override
  public ComparatorJob createInstance(Comparator comparator,
      ComparatorProperties comparatorProperties, List<DataFilterJob> dataFilterJobs)
      throws ParametersException {
    ClientSidePerformanceComparator clientSidePerformanceComparator = new ClientSidePerformanceComparator(
        artifactsDAO,
        clientSidePerformanceParser, comparatorProperties);
    clientSidePerformanceComparator.setParameters(comparator.getParameters());
    return clientSidePerformanceComparator;
  }
}
