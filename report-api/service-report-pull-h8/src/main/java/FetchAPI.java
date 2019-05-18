import com.eason.report.pull.h8.vo.fetchResultRo;
import com.eason.report.pull.h8.vo.fetchResultVo;
import com.eason.report.pull.h8.vo.fetchRo;
import com.eason.report.pull.h8.vo.fetchVo;

public interface FetchAPI {

    public fetchRo fetchOrder(fetchVo vo) throws Exception;

    public fetchResultRo fetchhResult(fetchResultVo vo);
}
