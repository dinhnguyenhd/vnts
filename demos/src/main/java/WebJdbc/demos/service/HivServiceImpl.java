package WebJdbc.demos.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import WebJdbc.demos.entity.CauHinh;
import WebJdbc.demos.entity.DoiTuong;
import WebJdbc.demos.entity.Place;
import WebJdbc.demos.entity.SearchHivRow;
import WebJdbc.demos.forms.SearchHivFrom;
import WebJdbc.demos.repositorys.CauHinhRepository;
import WebJdbc.demos.repositorys.HivRepository;

@Transactional
@Service
public class HivServiceImpl implements HivService {

	@Autowired
	private HivRepository hivRepository;
	@Autowired
	private CauHinhRepository cauHinhRepository;

	@Override
	public List<Place> listPlace() {
		return this.hivRepository.listPlace();
	}

	@Override
	public List<DoiTuong> listDoiTuong() {
		return this.hivRepository.listDoiTuong();
	}

	@Override
	public List<SearchHivRow> searchHiv(SearchHivFrom form) {
		List<CauHinh> list = cauHinhRepository.listConfig();
		Set<String> set = new HashSet<String>();
		for (CauHinh cauHinh : list) {
			StringTokenizer stok = new StringTokenizer(cauHinh.getListCode().trim(), ";");
			while (stok.hasMoreTokens()) {
				String s = stok.nextToken();
				set.add(s);
			}
		}
		StringJoiner join = new StringJoiner(",");
		for (String code : set) {
			join.add("'" + code + "'");
		}
		
		List<SearchHivRow> temps = this.hivRepository.searchHiv(form, new StringBuilder(join.toString()));
		for (SearchHivRow searchHivRow : temps) {
			CauHinh cauhinh = this.cauHinhRepository.getNameConfig(searchHivRow.getMaxn());
			if (cauhinh != null && cauhinh.getTenso().trim().length() > 0) {
				searchHivRow.setSp(cauhinh.getTenso());
			}

		}
		Map<String, List<SearchHivRow>> map = temps.stream().collect(Collectors.groupingBy(SearchHivRow::getSp));
		int index = 1;
		List<SearchHivRow> result = new ArrayList<SearchHivRow>();
		for (List<SearchHivRow> row : map.values()) {
			for (SearchHivRow item : row) {
				item.setIndex(index++);
				result.add(item);

			}
		}
		return result;
	}

}
