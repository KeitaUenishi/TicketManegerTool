package product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.domain.CustomerList;
import product.domain.LiveList;
import product.service.CustomerListService;
import product.service.LiveListService;

/**
 * ライブ日程のリストを表示するコントローラー
 * ログイン後始めに表示されるページ
 * @author keita
 *
 */

@Controller
@RequestMapping("liveList")
public class LiveListController {

	@Autowired
	private LiveListService liveListService;

	@Autowired
	private CustomerListService customerListService;

	/**
	 * @param model liveList.htmlへデータを持っていく
	 * @return liveList.htmlへ遷移
	 */

	// Live一覧画面の表示
	@GetMapping
	public String findAll(Model model) {
		List<LiveList> liveList = liveListService.findAll();
		model.addAttribute("liveList", liveList);
		return "liveList";
	}

	/**
	 * liveListデータ入力画面に遷移する
	 * @param model liveList.htmlへデータを持っていく
	 * @return liveList.htmlへ遷移
	 */

	// Live日程新規作成画面の表示
	@GetMapping("new")
	public String newLiveList(Model model) {
		return "liveList/new";
	}

	// Live日程編集画面の表示
	@GetMapping("{dateId}/edit")
	public String edit(@PathVariable Long dateId, Model model) {
		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "liveList/edit";
	}

	// 取り置きリスト表示画面の表示
	@GetMapping("{dateId}")
	public String show(@PathVariable Long dateId, Model model) {
		LiveList liveListChoise = liveListService.selectLiveList(dateId);
		List<CustomerList> customerLists = liveListChoise.getCustomers();
		model.addAttribute("customerLists", customerLists);

		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "liveList/show";
	}

	// Live日程データの保存
	@PostMapping
	public String create(@ModelAttribute LiveList liveList) {
		liveListService.insert(liveList);
		return "redirect:/liveList";
	}

	// Live日程データの更新
	@GetMapping("/update/{dateId}")
	@Transactional(readOnly = false)
	public String update(@PathVariable Long dateId, @ModelAttribute LiveList liveList) {
		liveList.setDateId(dateId);
		liveListService.update(liveList);
		return "redirect:/liveList";
	}

	// Live日程データの削除
	@PostMapping("/{dateId}")
	public String delete(@PathVariable Long dateId) {
		liveListService.delete(dateId);
		return "redirect:/liveList";
	}

	/**
	 * customer_listの編集
	 */

	//	// Live日程新規作成画面の表示
	//	@GetMapping("customerNew")
	//	public String newCustomerList(Model model) {
	//		return "redirect:/liveList";
	//	}
	//
	//	// customerデータの保存
	//	@PostMapping("liveList/customer")
	//	public String customerCreate(@ModelAttribute CustomerList customerList) {
	//		customerListService.insert(customerList);
	//		return "redirect:/liveList/{dateId}";
	//	}
	//
	//	// customerデータの更新
	//	@GetMapping("/update/{dateId}")
	//	@Transactional(readOnly = false)
	//	public String customerUpdate(@PathVariable Long id, @ModelAttribute CustomerList customerList) {
	//		customerList.setId(id);
	//		customerListService.update(customerList);
	//		return "redirect:/liveList";
	//	}
	//
	//	// customerデータの削除
	//	@PostMapping("/{id}")
	//	public String customerDelete(@PathVariable Long id) {
	//		customerListService.delete(id);
	//		return "redirect:/liveList";
	//	}
}
